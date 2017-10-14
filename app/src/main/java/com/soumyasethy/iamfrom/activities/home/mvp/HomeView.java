package com.soumyasethy.iamfrom.activities.home.mvp;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;

import com.soumyasethy.iamfrom.R;
import com.soumyasethy.iamfrom.activities.home.MainActivity;
import com.soumyasethy.iamfrom.activities.home.fragment.FriendRequest;
import com.soumyasethy.iamfrom.activities.home.fragment.UserRecommendation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("ViewConstructor")
public class HomeView extends FrameLayout {

    private final ProgressDialog progressDialog = new ProgressDialog(getContext());
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    MainActivity mainActivity = new MainActivity();

    private int[] tabIcons = {R.drawable.feed, R.drawable.add_usr, R.drawable.menu};

    public HomeView(Activity activity) {
        super(activity);

        inflate(getContext(), R.layout.home, this);

        progressDialog.setMessage("Looking up user");
        ButterKnife.bind(this);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(mainActivity.getSupportFragmentManager());
        adapter.addFragment(new UserRecommendation(), "feed");
        adapter.addFragment(new FriendRequest(), "Requests");
        adapter.addFragment(new FriendRequest(), "Account");
        viewPager.setAdapter(adapter);
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);

    }

    public void showLoading(boolean loading) {
        if (loading) {
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
    }

    /**
     * Making notification bar transparent
     */

    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    /*public void setMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public Observable<Void> observeButton() {
        return RxView.clicks(usernameLookUpBtn);
    }

    public String getUsernameEdit() {
        return usernameEditText.getText().toString();
    }

    public void showLoading(boolean loading) {
        if (loading) {
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
    }

    public void ShowToast(Details message) {
        Toast.makeText(getContext(), "Hello :" + message.userDetails.firstName, Toast.LENGTH_SHORT).show();
    }*/

}
