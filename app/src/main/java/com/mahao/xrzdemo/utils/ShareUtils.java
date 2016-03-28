package com.mahao.xrzdemo.utils;

import android.os.Handler;

import com.mahao.xrzdemo.bean.Event;
import com.mahao.xrzdemo.ui.BaseActivity;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners.SnsPostListener;
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.QZoneShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;

/**
 * 友盟分享 备注人： Alex_MaHao
 *
 * @date 创建时间：2016年2月23日 上午8:47:53
 */
public class ShareUtils {
    final UMSocialService mController = UMServiceFactory
            .getUMSocialService("com.umeng.share");

    private Event event;

    private OnShareListener l;

    private BaseActivity activity;

    private Handler handler = new Handler();
    /**
     * 分享方式
     */
    private SHARE_MEDIA shareChannel;

    public ShareUtils(BaseActivity activity, Event event, OnShareListener l) {
        this.activity = activity;
        this.l = l;
        this.event = event;
        uMeng();
        setShareContent();
    }

    private void uMeng() {
        String appId = "100424468";
        String appKey = "c7394704798a158208a74ab60104f0ba";
        // 添加QQ支持, 并且设置QQ分享内容的target url
        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(activity,
                appId, appKey);
        qqSsoHandler.setTargetUrl(event.getShareURL());
        qqSsoHandler.addToSocialSDK();

        // 添加QZone平台
        QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(activity, appId, appKey);
        qZoneSsoHandler.addToSocialSDK();
    }

    public void share() {
        mController.getConfig().setPlatforms(
                SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE);
        mController.openShare(activity, false);
    }

    /**
     * 根据不同的平台设置不同的分享内容</br>
     */
    private void setShareContent() {

        mController.setShareContent(event.getDetail());


        UMImage urlImage = new UMImage(activity, event.getImgs().get(0)
        );
        // UMImage resImage = new UMImage(getActivity(), R.drawable.icon);


        // UMEmoji emoji = new UMEmoji(getActivity(),
        // "http://www.pc6.com/uploadimages/2010214917283624.gif");
        // UMEmoji emoji = new UMEmoji(getActivity(),
        // "/storage/sdcard0/emoji.gif");

        WeiXinShareContent weixinContent = new WeiXinShareContent();
        weixinContent
                .setShareContent(event.getDetail());
        weixinContent.setTitle(event.getTitle());
        weixinContent.setTargetUrl(event.getShareURL());
        weixinContent.setShareMedia(urlImage);
        mController.setShareMedia(weixinContent);

        // 设置朋友圈分享的内容
        CircleShareContent circleMedia = new CircleShareContent();
        circleMedia
                .setShareContent(event.getDetail());
        circleMedia.setTitle(event.getTitle());
        circleMedia.setShareMedia(urlImage);
        // circleMedia.setShareMedia(uMusic);
        // circleMedia.setShareMedia(video);
        circleMedia.setTargetUrl(event.getShareURL());
        mController.setShareMedia(circleMedia);

        UMImage qzoneImage = new UMImage(activity,
                event.getImgs().get(0));
        qzoneImage
                .setTargetUrl(event.getShareURL());

        // 设置QQ空间分享内容
        QZoneShareContent qzone = new QZoneShareContent();
        qzone.setShareContent(event.getDetail());
        qzone.setTargetUrl(event.getShareURL());
        qzone.setTitle(event.getTitle());
        qzone.setShareMedia(urlImage);
        // qzone.setShareMedia(uMusic);
        mController.setShareMedia(qzone);


        QQShareContent qqShareContent = new QQShareContent();
        qqShareContent.setShareContent(event.getDetail());
        qqShareContent.setTitle(event.getTitle());
        qqShareContent.setShareMedia(urlImage);
        qqShareContent.setTargetUrl(event.getShareURL());
        mController.setShareMedia(qqShareContent);


    }

    /**
     * 分享监听器
     */
    SnsPostListener mShareListener = new SnsPostListener() {

        @Override
        public void onStart() {

        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int stCode,
                               SocializeEntity entity) {
            if (stCode == 200) {
                l.onResponse(true);

            } else {
                l.onResponse(false);
            }

        }

    };

    /**
     * 分享的接口回调 备注人： Alex_MaHao
     *
     * @date 创建时间：2016年2月23日 上午9:06:10
     */
    public interface OnShareListener {
        void onResponse(boolean isShare);
    }
}
