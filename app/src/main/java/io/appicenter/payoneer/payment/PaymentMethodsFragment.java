package io.appicenter.payoneer.payment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import io.appicenter.domain.model.PaymentMethod;
import io.appicenter.payoneer.R;
import io.appicenter.payoneer.databinding.PaymentMethodsFragmentBinding;
import io.appicenter.payoneer.utils.ErrorParser;
import io.appicenter.payoneer.utils.NoNetworkException;
import io.appicenter.payoneer.utils.Response;

/**
 * Fragment for the payment methods list
 */
public class PaymentMethodsFragment extends DaggerFragment {

    @Inject
    PaymentViewModelFactory factory;

    @Inject
    ErrorParser errorParser;

    private PaymentMethodsAdapter adapter;
    private PaymentMethodsFragmentBinding binding;
    private Snackbar sb;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = PaymentMethodsFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        //Initialize recycler
        adapter = new PaymentMethodsAdapter();
        binding.paymentMethodsRecyclerView.setAdapter(adapter);

        sb = Snackbar.make(binding.paymentMethodsLayout, "", Snackbar.LENGTH_SHORT);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        PaymentViewModel viewModel = new ViewModelProvider(this, factory).get(PaymentViewModel.class);
        viewModel.paymentMethods.observe(getViewLifecycleOwner(), this::parseOrder);
        binding.productsEmptyView.emptyRetry.setOnClickListener(view -> {
            viewModel.getPaymentMethods();
        });
        viewModel.getPaymentMethods();
    }


    private void parseOrder(Response<List<PaymentMethod>> response) {
        setIdleState();
        switch (response.status) {
            case ERROR:
                if (response.error.getClass() == NoNetworkException.class) {
                    handleNoNetwork();
                } else {
                    showErrorSnack(errorParser.parse(response.error));
                }
                break;
            case LOADING:
                binding.paymentMethodsProgress.setVisibility(View.VISIBLE);
                break;
            case SUCCESS:
                adapter.submitList(response.data);
                break;
        }

    }

    private void showErrorSnack(String msg) {
        sb.setDuration(Snackbar.LENGTH_SHORT);
        sb.setText(msg);
        sb.getView().setBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.grey_90));
        sb.show();
    }

    private void handleNoNetwork() {
        if (adapter.getCurrentList().isEmpty()) {
            binding.productsEmptyView.getRoot().setVisibility(View.VISIBLE);
        } else {
            showErrorSnack(getString(R.string.notification_cached_data));
        }
    }

    private void setIdleState() {
        binding.productsEmptyView.getRoot().setVisibility(View.GONE);
        binding.paymentMethodsProgress.setVisibility(View.GONE);
    }
}