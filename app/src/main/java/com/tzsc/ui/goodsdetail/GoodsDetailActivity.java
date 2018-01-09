package com.tzsc.ui.goodsdetail;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.tzsc.R;
import com.tzsc.base.BaseActivity;
import com.tzsc.utils.Glider;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class GoodsDetailActivity extends BaseActivity {
    /**
     * 广告轮播
     */
    @BindView(R.id.home_banner)
    Banner banner;
    @BindView(R.id.rv_coment_list)
    RecyclerView rvComentList;

    @Override
    public boolean isNeedBack() {
        return true;
    }

    @Override
    public CharSequence setTitleText() {
        return "商品详情";
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_goods_detail;
    }

    @Override
    public void initData() {
        //设置图片加载器
        banner.setImageLoader(new ImageLoader() {

            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Glider.getInstance().load(path, imageView);
            }
        });
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //设置图片集合
        banner.setImages(Arrays.asList("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1348228688,3253866962&fm=27&gp=0.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1513102380867&di=d8a3bd7cdbc67ea69c032582653aa3d7&imgtype=0&src=http%3A%2F%2Fc.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F37d12f2eb9389b50a53c45838f35e5dde6116e24.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1513102380867&di=1ce1f105857048de72a948e9c3a9d782&imgtype=0&src=http%3A%2F%2Fd.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fb8389b504fc2d562a68dbc07ed1190ef77c66c56.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1513102380867&di=adb786571970cdb47e8f97d1ddeb62a5&imgtype=0&src=http%3A%2F%2Fb.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F9213b07eca8065387de97cc29ddda144ac3482e4.jpg"));
        banner.setDelayTime(2500);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        rvComentList.setLayoutManager(new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        List<String> data=new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            data.add("lsdjgla"+i);
        }
        rvComentList.setAdapter(new CommonAdapter<String>(this,R.layout.item_comment,data) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {

            }
        });
    }
}
