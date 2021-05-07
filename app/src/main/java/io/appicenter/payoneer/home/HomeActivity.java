package io.appicenter.payoneer.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.appicenter.payoneer.R;
import io.appicenter.payoneer.databinding.ActivityHomeBinding;


/**
 * Main activity for the app. Holds the navigation host fragment.
 */
public class HomeActivity extends DaggerAppCompatActivity {

    @Inject
    HomeViewModelFactory homeViewModelFactory;

    private ActivityHomeBinding binding;
    private Snackbar sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setSupportActionBar(binding.toolbar);

        //Initialize view model
        HomeViewModel viewModel = new ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel.class);

        //observe network status
        viewModel.networkState.observe(this, this::parseNetworkStatus);

        //Setup Nav UI
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(binding.collapsingToolbarLayout, binding.toolbar, navController, appBarConfiguration);

        sb = Snackbar.make(binding.homeActivityLayout, "", Snackbar.LENGTH_SHORT);
        viewModel.observeNetwork();
    }

    private void parseNetworkStatus(Boolean networkStatus) {

        if (networkStatus) {
            if (sb.isShown()) {
                sb.setText(R.string.notification_reconnect);
                sb.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.light_green_500));
                new Handler(Looper.getMainLooper()).postDelayed(() -> sb.dismiss(),1500);

            }
        } else {
            sb.setDuration(Snackbar.LENGTH_INDEFINITE);
            sb.setText(R.string.notification_no_network);
            sb.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.grey_90));
            sb.show();
        }

    }


}