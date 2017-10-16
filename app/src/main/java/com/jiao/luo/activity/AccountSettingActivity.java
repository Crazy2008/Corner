package com.jiao.luo.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.bean.TieBean;
import com.dou361.dialogui.listener.DialogUIItemListener;
import com.jiao.luo.R;
import com.jiao.luo.Response.ResImg;
import com.jiao.luo.Response.ResPub;
import com.jiao.luo.utils.Constant;
import com.jiao.luo.utils.CutBitmapUtils;
import com.jiao.luo.utils.DataFilePersistenceUtils;
import com.jiao.luo.utils.GlideUtils;
import com.jiao.luo.utils.GsonTools;
import com.jiao.luo.widget.MyDialog;
import com.jiao.luo.widget.MyToast;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.common.base.ActivityCommBase;
import cn.common.http2.callback.SimpleCommonCallback;
import okhttp3.Call;
import okhttp3.Response;

import static com.dou361.dialogui.DialogUIUtils.dismiss;


/**
 * 账户设置ZHANGHU SHEZHI
 */

public class AccountSettingActivity extends ActivityCommBase {

    private ImageView civ_icon;
    private static String fileCameraPath;/*拍照的文件咱径*/
    private static String fileSelectPath;/*选择文件的路径*/
    private static String filecropPath;// 切割文件的路径
    File resultFile = null;
    private MyDialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);
        ButterKnife.bind(this);
        civ_icon = (ImageView) findViewById(R.id.civ_icon);
        initView();
        initData();

    }

    private void initView() {
        if (!"".equals(Constant.HEADPIC)&&Constant.HEADPIC!=null) {
            GlideUtils.load(this,Constant.HEADPIC,civ_icon);
        }
    }

    private void initData() {

    }

    @OnClick({R.id.iv_back, R.id.civ_icon, R.id.rl_student, R.id.rl_motify_pwd, R.id.btn_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.civ_icon:
                showDialog();
                break;
            case R.id.rl_student:
                break;
            //修改密码
            case R.id.rl_motify_pwd:
                Intent intent = new Intent(this, RegisterActivity.class);
                Constant.ISREGISTER=false;
                startActivity(intent);

                break;
            case R.id.btn_exit:


                showExitDialog();
                break;
        }
    }

    private void showExitDialog() {

        myDialog = new MyDialog(this);
        myDialog.setMessage("确定要退出吗？");
        myDialog.setYesOnclickListener("确定", new MyDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                myDialog.dismiss();
                Constant.clearLoginData();
                Constant.clearLoginSPData(AccountSettingActivity.this);
                loginOut();
            }
        });
        myDialog.setNoOnclickListener("取消", new MyDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }

    //退出登录
    private void loginOut() {
        OkGo.post(Constant.LOGOUT)
                .headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID)
                .execute(new SimpleCommonCallback<ResPub>(AccountSettingActivity.this) {
                    @Override
                    public void onSuccess(ResPub resPub, Call call, Response response) {
                            if(resPub.getReturnCode()==1){
                                AccountSettingActivity.this.finish();
                            }else{
                                AccountSettingActivity.this.finish();
                            }
                    }
                });
    }

    private void showDialog() {
        List<TieBean> strings = new ArrayList<TieBean>();
        strings.add(new TieBean("拍照上传"));
        strings.add(new TieBean("本地上传"));
        DialogUIUtils.showSheet(this, strings, "取消", Gravity.BOTTOM, true, true, new DialogUIItemListener() {
            @Override
            public void onItemClick(CharSequence text, int position) {
                if (position == 0) {
                      /*拍照*/
                    Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File imageFile = DataFilePersistenceUtils.getFile("camera" + new Date().getTime() + ".jpg");
                    fileCameraPath = imageFile.getAbsolutePath();
                    Uri imageUri = Uri.fromFile(imageFile);
                    intent2.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent2, 2);// 采用ForResult打开
                } else {
                      /*在相册中选取*/
                    Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                    intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    startActivityForResult(intent1, 1);
                    dismiss();
                }
            }

            @Override
            public void onBottomBtnClick() {
                dismiss();
            }
        }).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    /*从图库选择图片*/
                    fileSelectPath = parseFilePath(data.getData());
//                    cropPhoto(data.getData());
                    try {
                        resultFile = new File(CutBitmapUtils.getCompressFile(fileSelectPath));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (resultFile == null) {
                        return;
                    }
                    System.out.println("path3" + resultFile.getTotalSpace());
                    submitImage();
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    /*拍照图片*/
//                    cropPhoto(Uri.fromFile(new File(fileCameraPath)));// 裁剪图片
                    try {
                        resultFile = new File(CutBitmapUtils.getCompressFile(fileCameraPath));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (resultFile == null) {
                        return;
                    }
                    System.out.println("path3" + resultFile.getTotalSpace());
                    submitImage();

                }
                break;
            case 3:
                resultFile = null;
                if (new File(filecropPath).exists()) {
                    resultFile = new File(filecropPath);
                } else if (handle(fileCameraPath)) {
                    resultFile = new File(fileCameraPath);
                } else if (new File(fileSelectPath).exists()) {
                    resultFile = new File(fileSelectPath);
                }

                if (resultFile == null) {
                    return;
                }
                System.out.println("path1" + resultFile.getAbsolutePath());
                System.out.println("path2" + resultFile.getPath());
                System.out.println("path3" + resultFile.getTotalSpace());
                submitImage();
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public boolean handle( String path){
        if(path==null)return false;
        return new File(path).exists();
    }

    //上传头像
    private void submitImage() {
        OkGo.post(Constant.EDITIMG)
                .headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID)
                .params("file", resultFile).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                ResImg resImg = GsonTools.changeGsonToBean(s, ResImg.class);
                if (resImg.getReturnCode() == 1) {
                    //设置头像
                    new MyToast(AccountSettingActivity.this, "上传成功");
                    GlideUtils.load(AccountSettingActivity.this,resultFile.getAbsolutePath(),civ_icon);
                    String image_url = resImg.getData().getImage_url();
                    if (!image_url.equals("")) {
                        Constant.HEADPIC = image_url;
                    }
                } else {
                    toast(resImg.getMessage());
                }
            }
        });

    }

    /**
     * 数据库中找文件路径
     *
     * @param uri
     * @return
     */
    private String parseFilePath(Uri uri) {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        return picturePath;
    }

    /**
     * 调用系统的裁剪功能
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 480);
        intent.putExtra("outputY", 480);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        File imageFile = DataFilePersistenceUtils.getFile("crop" + new Date().getTime() + ".jpg");
        this.filecropPath = imageFile.getAbsolutePath();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(filecropPath)));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());//裁剪成的图片的格式
        startActivityForResult(intent, 3);
    }
}
