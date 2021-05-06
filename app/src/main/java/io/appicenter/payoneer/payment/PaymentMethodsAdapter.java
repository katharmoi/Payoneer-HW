package io.appicenter.payoneer.payment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import io.appicenter.domain.model.PaymentMethod;
import io.appicenter.payoneer.R;
import io.appicenter.payoneer.databinding.ListItemPaymentMethodsBinding;

public class PaymentMethodsAdapter extends ListAdapter<PaymentMethod, PaymentMethodsAdapter.ViewHolder> {


    PaymentMethodsAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.from(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public ListItemPaymentMethodsBinding binding = ListItemPaymentMethodsBinding.bind(itemView);

        private ViewHolder(View itemView) {
            super(itemView);
        }

        static ViewHolder from(ViewGroup parent) {
            return new ViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_payment_methods, parent, false));
        }

        public void bind(PaymentMethod data) {
            binding.methodName.setText(data.label);
            Picasso.get().load(data.links.logo).into(binding.logo);
        }

    }


    public static final DiffUtil.ItemCallback<PaymentMethod> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<PaymentMethod>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull PaymentMethod oldMethod, @NonNull PaymentMethod newMethod) {
                    return oldMethod.code.equals(newMethod.code);
                }

                @Override
                public boolean areContentsTheSame(
                        @NonNull PaymentMethod oldMethod, @NonNull PaymentMethod newMethod) {
                    return oldMethod.equals(newMethod);
                }
            };
}
