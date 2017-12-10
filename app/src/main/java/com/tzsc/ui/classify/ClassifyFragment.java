package com.tzsc.ui.classify;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.tzsc.R;
import com.tzsc.base.BaseMvpFragment;
import com.tzsc.base.BasePresenter;
import com.tzsc.entity.ClassifyVo;
import com.tzsc.utils.Glider;
import com.tzsc.widget.TitleBarView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ClassifyFragment extends BaseMvpFragment {
    /**
     * 标题
     */
    @BindView(R.id.tb_title)
    TitleBarView tvTitle;

    @BindView(R.id.rv_classify_list)
    RecyclerView rvClassifyList;


    List<ClassifyVo> classifyVoList = new ArrayList<>(12);

    {
        classifyVoList.add(new ClassifyVo(R.mipmap.ic_classify_daibu, "校园代步", "自行车 电动车"));
        classifyVoList.add(new ClassifyVo(R.mipmap.ic_classify_huawei, "手机", "华为 iPhone Vivo  小米"));
        classifyVoList.add(new ClassifyVo(R.mipmap.ic_classify_cirl, "衣物伞帽", "上衣 裤子 背包"));
        classifyVoList.add(new ClassifyVo(R.mipmap.ic_classify_book, "图书教材", "教材 考研 申论 公务员 银行"));
        classifyVoList.add(new ClassifyVo(R.mipmap.ic_classify_mobile_computer, "电脑", "联想 戴尔 Mac"));
        classifyVoList.add(new ClassifyVo(R.mipmap.ic_classify_peijian, "数码配件", "耳机 U盘 键盘"));
        classifyVoList.add(new ClassifyVo(R.mipmap.ic_classify_photo, "数码", "iPad 相机 单反"));
        classifyVoList.add(new ClassifyVo(R.mipmap.ic_classify_dianqi, "电器", "电扇 台灯 空气净化器"));
        classifyVoList.add(new ClassifyVo(R.mipmap.ic_classify_sport, "运动健身", "篮球 足球 球拍"));
        classifyVoList.add(new ClassifyVo(R.mipmap.ic_classify_wifi, "校园网", "3个月 半年  一年"));
        classifyVoList.add(new ClassifyVo(R.mipmap.ic_classify_yule, "生活娱乐", "乐器 日常 会员卡"));
        classifyVoList.add(new ClassifyVo(R.mipmap.ic_classify_other, "其他", "可能有您感兴趣的东西"));
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_classify;
    }

    @Override
    public void initData() {
        tvTitle.setTitle("分类");
        tvTitle.setLeftVisible(false);
        tvTitle.setTitleColor(Color.WHITE);
        //不让其获取焦点
        rvClassifyList.setFocusable(false);
        rvClassifyList.setLayoutManager(new GridLayoutManager(mContext, 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvClassifyList.setAdapter(new CommonAdapter<ClassifyVo>(mContext, R.layout.item_classify_all, classifyVoList) {
            @Override
            protected void convert(ViewHolder holder, ClassifyVo classifyVo, int position) {
                ImageView ivIcon = holder.getView(R.id.iv_classify_icon);
                Glider.getInstance().load(classifyVo.getIconResId(), ivIcon);
                holder.setText(R.id.tv_classify_title, classifyVo.getTitle());
                holder.setText(R.id.tv_classify_desc, classifyVo.getDesc());
            }
        });
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

}
