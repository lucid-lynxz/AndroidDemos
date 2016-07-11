package org.lynxz.robolectricdemo.util;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import org.lynxz.robolectricdemo.R;

public class DialogUtils {
    public static Dialog createLoadingDialog(Context ctx) {

        Dialog loadingDialog = new Dialog(ctx, R.style.loading_dialog);

        View inflate = View.inflate(ctx, R.layout.layout_loading, null);
        loadingDialog.setContentView(inflate);

        return loadingDialog;
    }
}