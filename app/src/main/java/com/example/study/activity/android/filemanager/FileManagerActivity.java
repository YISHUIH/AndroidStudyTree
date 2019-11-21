package com.example.study.activity.android.filemanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.example.study.BaseActivity;
import com.example.study.R;
import com.example.study.util.GetPathFromUri;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FileManagerActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, FileManagerActivity.class);
        context.startActivity(starter);
    }

    @BindView(R.id.path)
    TextView path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_manager);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.open)
    public void onViewClicked() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("video/*;image/*;audio/*");
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 1);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {//是否选择，没选择就不会继续
            Uri uri = data.getData();//得到uri，后面就是将uri转化成file的过程。
//            Cursor actualimagecursor = getContentResolver().query(uri, proj, null, null, null);
//            assert actualimagecursor != null;
//            int actual_image_column_index = actualimagecursor.getColumnIndex(MediaStore.Files.FileColumns._ID);
//            actualimagecursor.moveToFirst();
//            String img_path = actualimagecursor.getString(actual_image_column_index);
//
//            File file = new File(img_path);
//            leftPath.setText(file.getAbsolutePath());
//            Toast.makeText(this, file.toString(), Toast.LENGTH_SHORT).show();
//            actualimagecursor.close();
            String s = GetPathFromUri.getPath(this, uri);
            path.setText(s);
        }
    }

}
