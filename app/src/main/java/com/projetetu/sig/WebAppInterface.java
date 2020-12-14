package com.projetetu.sig;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;

public class WebAppInterface {
    public Context mContext;

    WebAppInterface(Context c) {
        mContext = c;
    }

    @JavascriptInterface
    public void toRoom(String id, String fonction, String etage){
        Intent i = new Intent(mContext, PieceActivity.class);
        i.putExtra("id",id);
        i.putExtra("fonction",fonction);
        i.putExtra("etage",etage);
        mContext.startActivity(i);
    }
}
