package com.example.pdfprime.presentation.utils;

import static java.lang.Boolean.FALSE;

public class Constants {
    //Options for BottomSheets
    public static final int NEWDOC_DIS = 1;
    public static final int NEWDOC_CAM = 2;
    public static final int NEWDOC_TXT = 3;

    public static final int DOC_EDIT = 101;
    public static final int DOC_SHARE = 102;
    public static final int DOC_DELETE = 103;
    public static final int DOC_OPEN = 104;

    //Name of data between destinations
    public static final String DOCUMENT = "DOCUMENT";

    public static final int REQUEST_IMAGE_CAPTURE = 200;
    public static final int REQUEST_IMAGE_CROP = 201;

    //Keys of shared preferences
    public static final String PAGE_SIZE = "PAGE_SIZE";


    //Default values for first instalation
    public static final String IMAGE_QUA_K = "IMAGE_QUA_K";
    public static final String ASK_FP_K = "ASK_FP_K";


    public static final float IMAGE_QUA_DEF = 0.2F;
    public static final Boolean ASK_FP_DEF = false;
}
