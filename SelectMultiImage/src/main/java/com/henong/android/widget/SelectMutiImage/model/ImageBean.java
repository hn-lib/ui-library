package com.henong.android.widget.SelectMutiImage.model;

import android.graphics.Bitmap;

import com.henong.android.widget.SelectMutiImage.SelectBitmapUtil;

import java.io.IOException;

/**
 * Created by Hankkin on 15/8/20.
 */
public class ImageBean {
	public String id;
	public String path;
	private Bitmap bitmap;
	public String thumbnailPath;

	public String getThumbnailPath() {
		return thumbnailPath;
	}

	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Bitmap getBitmap() {
		if (bitmap == null) {
			try {
				bitmap = SelectBitmapUtil.revitionImageSize(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
}
