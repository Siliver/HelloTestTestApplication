package com.didispace.elsaticclient.Config;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexResponse;

public class CustomActionListener implements ActionListener<IndexResponse> {
    /**
     * 成功执行时调用
     * @param indexResponse 搜索成功时回调
     */
    @Override
    public void onResponse(IndexResponse indexResponse) {
        //获取索引
        String index=indexResponse.getIndex();
        //获取当前索引的ID
        String id=indexResponse.getId();
        if(indexResponse.getResult()== DocWriteResponse.Result.CREATED){
            //第一次创建文旦时

        }else if (indexResponse.getResult()==DocWriteResponse.Result.UPDATED){
            //索引更新时回调
        }
    }

    /**
     * 整个失败时调用
     * @param e 调用失败的异常信息
     */
    @Override
    public void onFailure(Exception e) {

    }
}
