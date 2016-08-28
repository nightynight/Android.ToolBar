package com.brokepal.toolbardemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {
    public Toolbar toolbar;
    ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildToolbar(R.id.toolbar_main);//初始化ToolBar
        buildDrawer(R.id.drawer_layout, R.id.navigation_drawer, R.layout.user_info);//初始化DrawerNavigation
    }

    public void buildToolbar(int id) {
        toolbar = (Toolbar) findViewById(id);
        setSupportActionBar(toolbar);
    }

    /**
     * 初始化DrawerNavigation
     * @param drawerLayoutId
     * @param naviDrawerId
     * @param naviDrawerHeaderRes NavigationView支持在导航条目之前放入头部信息，比如QQ的用户信息，该参数为控制头部信息的xml布局文件的id
     */
    public void buildDrawer(int drawerLayoutId, int naviDrawerId, int naviDrawerHeaderRes) {
        drawerLayout = (DrawerLayout) findViewById(drawerLayoutId);
        navigationView = (NavigationView) findViewById(naviDrawerId);
        if (toolbar != null) {
            //Construct a new ActionBarDrawerToggle with a Toolbar.
            mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                    R.string.app_name, R.string.app_name);
            mDrawerToggle.syncState();//给drawerNavigation加上触发按钮
            navigationView.inflateHeaderView(naviDrawerHeaderRes);//给drawerNavigation加上头部

            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    drawerLayout.closeDrawers();
                    onNavidrawerItemSelected(menuItem);
                    return true;
                }
            });
        }
    }

    //对菜单的点击做出回应，这里只给菜单1添加一个打开另一个Activity的动作
    public boolean onNavidrawerItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id) {
            case R.id.navi_drawer_new_task:
                Intent intent = new Intent(this, TestActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}
