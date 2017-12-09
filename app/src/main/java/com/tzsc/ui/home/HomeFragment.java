package com.tzsc.ui.home;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.tzsc.R;
import com.tzsc.base.BaseMvpFragment;
import com.tzsc.base.BasePresenter;
import com.tzsc.utils.Glider;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseMvpFragment {
    /**
     * 广告轮播
     */
    @BindView(R.id.home_banner)
    Banner banner;

    @BindView(R.id.rv_goods_list_new)
    RecyclerView rvGoodsListNew;

    @BindView(R.id.rv_goods_list_pai)
    RecyclerView rvGoodsListPai;

    @BindView(R.id.sv_home)
    ScrollView scrollView;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initData() {
        //默认显示最顶部
        scrollView.smoothScrollTo(0, 0);
        //不让其获取焦点
        rvGoodsListNew.setFocusable(false);
        rvGoodsListPai.setFocusable(false);
        //设置图片加载器
        banner.setImageLoader(new ImageLoader() {

            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glider.load(path, imageView);
            }
        });
        //设置图片集合
        banner.setImages(Arrays.asList("http://img.zcool.cn/community/01ea0657cce2e00000012e7e322bc9.jpg@900w_1l_2o_100sh.jpg",
                "http://img.zcool.cn/community/0164c45940f898a8012193a3ee8db7.jpg@1280w_1l_2o_100sh.jpg",
                "http://img.zcool.cn/community/01a7ca58df3145a801219c77b4110e.jpg@2o.jpg",
                "http://www.yongxintex.com/Html/images/b1.jpg",
                "http://img.zcool.cn/community/012d3658de20b6a801219c77083959.jpg"));
        banner.setDelayTime(2500);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        rvGoodsListNew.setLayoutManager(new GridLayoutManager(mContext, 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvGoodsListPai.setLayoutManager(new LinearLayoutManager(mContext) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            data.add("lsdjgla" + i);
        }
        rvGoodsListNew.setAdapter(new CommonAdapter<String>(mContext, R.layout.item_home_goods_new, data) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {

            }
        });
        rvGoodsListPai.setAdapter(new CommonAdapter<String>(mContext, R.layout.item_home_goods_pai, data) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

}
