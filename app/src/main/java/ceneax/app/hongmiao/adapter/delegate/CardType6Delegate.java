package ceneax.app.hongmiao.adapter.delegate;

import com.google.gson.Gson;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import ceneax.app.hongmiao.bean.WeiboTopicCardsBean;
import ceneax.app.hongmiao.util.L;

public class CardType6Delegate implements ItemViewDelegate<WeiboTopicCardsBean> {

    @Override
    public int getItemViewLayoutId() {
        return android.R.layout.simple_list_item_1;
    }

    @Override
    public boolean isForViewType(WeiboTopicCardsBean item, int position) {
        L.e("-----------------", position + "--------" + new Gson().toJson(item));
        return item.getCardType() == 6 || item.getCardType() == 7;
    }

    @Override
    public void convert(ViewHolder holder, WeiboTopicCardsBean bean, int position) {
        holder.setText(android.R.id.text1, bean.getDesc());
    }

}
