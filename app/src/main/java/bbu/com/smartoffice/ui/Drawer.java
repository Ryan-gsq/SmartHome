package bbu.com.smartoffice.ui;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bbu.com.smartoffice.ManageActivity;
import bbu.com.smartoffice.R;
import bbu.com.smartoffice.adapter.DrawerRvAdapter;
import bbu.com.smartoffice.base.BaseFragment;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by G on 2016/11/16 0016.
 */

public class Drawer extends BaseFragment implements DrawerRvAdapter.OnItemClickListener {

    @Bind(R.id.recycleView)
    RecyclerView recycleView;

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_drawer, container, false);
        ButterKnife.bind(this, rootView);

        DrawerRvAdapter adapter = new DrawerRvAdapter();
        adapter.setClickListener(this);

        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recycleView.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClickListener(int position) {
        final Energy fragment = BaseFragment.getInstance(Energy.class);
        switch (position) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
        }
        ManageActivity.manageActivity.drawerLayout.closeDrawer(Gravity.LEFT);
        ManageActivity.manageActivity.drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                FragmentTransaction fragmentTransaction = ManageActivity.manageActivity.showFragment(fragment, true);
                fragmentTransaction.hide(BaseFragment.getInstance(Main.class)).commitAllowingStateLoss();
            }
        });
    }
}
