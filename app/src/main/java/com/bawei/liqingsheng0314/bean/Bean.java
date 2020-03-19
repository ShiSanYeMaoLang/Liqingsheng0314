package com.bawei.liqingsheng0314.bean;

import java.util.List;

/**
 * datetime:2020/3/14
 * author:刘庆生(lenovo)
 * function:
 */
public class Bean {

    /**
     * code : 200
     * msg : 请求成功
     * result : [{"desc":"自定义文本控件，支持富文本，包含两种状态：编辑状态和预览状态。编辑状态中，可以对插入本地或者网络图片，可以同时插入多张有序图片和删除图片，支持图文混排，并且可以对文字内容简单操作加粗字体，设置字体下划线，支持设置文字超链接(超链接支持跳转)，支持字数和图片数量统计，功能完善中\u2026\u2026","url":"https://github.com/yangchong211/YCCustomText","images":["http://img.gank.io/47e11fc4-d522-44a1-ba21-cd6cba4e22d8","http://img.gank.io/fa4b83e8-03f6-4d04-8a3e-8db357cc9238","http://img.gank.io/d8d62f1c-ebc0-4362-a23d-3adbdaf56d79","http://img.gank.io/effcdf25-1a26-48dc-bee7-66374b0af1d5","http://img.gank.io/9ac91b47-5778-4ce3-b534-34b6cd0f975d"]},{"desc":"RxHttp 一条链发送任意请求，让你眼前一亮的Http请求框架","url":"https://juejin.im/post/5ded221a518825125d14a1d4"},{"desc":"程序员的年底总结，带你了解 Github 爆款的实现。","url":"https://juejin.im/post/5df701b751882512290f25f2"},{"desc":"年底了，聊聊年终奖的套路。祝大家都避开套路，多拿大奖","url":"https://mp.weixin.qq.com/s/AavPrNwzF35cn-CUHgz8YQ"},{"desc":"懒到不想打包，念段咒语！让代码自己打包 ---- 念段咒语！让代码自己打包。","url":"https://mp.weixin.qq.com/s/OBz9rKkdyHBzj3K_893d7A"}]
     */

    private int code;
    private String msg;
    private List<ResultBean> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * desc : 自定义文本控件，支持富文本，包含两种状态：编辑状态和预览状态。编辑状态中，可以对插入本地或者网络图片，可以同时插入多张有序图片和删除图片，支持图文混排，并且可以对文字内容简单操作加粗字体，设置字体下划线，支持设置文字超链接(超链接支持跳转)，支持字数和图片数量统计，功能完善中……
         * url : https://github.com/yangchong211/YCCustomText
         * images : ["http://img.gank.io/47e11fc4-d522-44a1-ba21-cd6cba4e22d8","http://img.gank.io/fa4b83e8-03f6-4d04-8a3e-8db357cc9238","http://img.gank.io/d8d62f1c-ebc0-4362-a23d-3adbdaf56d79","http://img.gank.io/effcdf25-1a26-48dc-bee7-66374b0af1d5","http://img.gank.io/9ac91b47-5778-4ce3-b534-34b6cd0f975d"]
         */

        private String desc;
        private String url;
        private List<String> images;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
