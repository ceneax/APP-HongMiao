package ceneax.app.hongmiao.adapter.delegate;

import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import ceneax.app.hongmiao.R;
import ceneax.app.hongmiao.bean.WeiboTopicCardsBean;

public class CardTypeNoneDelegate implements ItemViewDelegate<WeiboTopicCardsBean> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_none;
    }

    @Override
    public boolean isForViewType(WeiboTopicCardsBean item, int position) {
        return item.getCardType() != 6 && item.getCardType() != 7 && item.getCardType() != 9;
    }

    @Override
    public void convert(ViewHolder holder, WeiboTopicCardsBean weiboTopicCardsBean, int position) {
    }

}
