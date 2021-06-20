package com.jimzmx.pdfprime.presentation.utils;

public class Constants {
    //Options for BottomSheets
    public static final int NEWDOC_DIS = 1;
    public static final int NEWDOC_CAM = 2;
    public static final int NEWDOC_TXT = 3;
    public static final int NEWDOC_GAL = 4;

    public static final int DOC_EDIT = 101;
    public static final int DOC_SHARE = 102;
    public static final int DOC_DELETE = 103;
    public static final int DOC_OPEN = 104;

    //Name of data between destinations
    public static final String DOCUMENT = "DOCUMENT";
    public static final String IMAGES = "IMAGES";
    public static final String IMAGE = "IMAGE";

    public static final int REQUEST_IMAGE_CAPTURE = 200;
    public static final int REQUEST_IMAGE_CROP = 201;
    public static final int REQUEST_IMAGES_GAL = 202;

    //Default values for first instalation
    public static final String PAGE_SIZE_K = "PAGE_SIZE_K";
    public static final String IMAGE_QUA_K = "IMAGE_QUA_K";
    public static final String ASK_FP_K = "ASK_FP_K";
    public static final String NSAF_NOT_EDITABLE_PAGE_K = "NSAF_NOT_EDITABLE_PAGE_K"; //NOT SHOW AGAIN FLAG


    public static final float IMAGE_QUA_DEF = 0.2F;
    public static final Boolean ASK_FP_DEF = false;
    public static final String PAGE_SIZE_DEF = "[0.0,0.0,612.0,792.0]";
    public static final Boolean NSAF_NOT_EDITABLE_PAGE_DEF = false;

    public static final String DELIMITER = "*:?<>";
}
