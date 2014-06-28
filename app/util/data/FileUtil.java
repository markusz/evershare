package util.data;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.filters.Canvas;
import net.coobird.thumbnailator.filters.ImageFilter;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;
import play.Play;
import util.constants.Constants;

import com.google.common.io.Files;

import models.wrapper.OfferWrapper;
import controllers.actions.VerboseAction.Verbose;
import controllers.db.OfferDAO;
import controllers.db.UserDAO;

public class FileUtil {

	public static void uploadOfferImageOnInit(OfferWrapper of, String path) {
	
		FileUtil.uploadOfferImage(of, of.getBaseModel().imgUrl, new File(path + "" + of.getBaseModel().imgUrl));
	}

	@Verbose
	public static void uploadOfferImage(OfferWrapper of, String fileName, File file) {
	
		String imgRootPath = Play.application().configuration()
				.getString("offerRootImgPath");
		String imgPath = Play.application().configuration()
				.getString("offerImgPath");
	
		String internalPath = imgRootPath + "/" + imgPath
				+ of.getObjectId().toString();
		String imagePath = Play.application().path().toString() + "/"
				+ internalPath;
	
		imagePath = imagePath.replace("\\", "/");
	
		new File(imagePath).mkdirs();
		File rename = new File(imagePath, fileName);
		
		try {
			Files.copy(file, new File(imagePath + "/" + fileName));
			ImageFilter imf = new Canvas(Constants.OFFER_THUMBNAIL_MAX_WIDTH, Constants.OFFER_THUMBNAIL_MAX_HEIGHT, Positions.CENTER, new Color(241, 241, 241));
			Thumbnails.of(rename).size(Constants.OFFER_THUMBNAIL_MAX_WIDTH, Constants.OFFER_THUMBNAIL_MAX_HEIGHT).addFilter(imf).toFiles(Rename.PREFIX_DOT_THUMBNAIL);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		of.getBaseModel().titleimage = fileName;
		try {
			OfferDAO.getDAO().upsert(of.getObjectId().toString(),
					"baseEntity.titleimage", fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void uploadUserProfileAvatar(String uid, String fileName,
			File file) {
		
		
		String imgRootPath = Play.application().configuration()
				.getString("offerRootImgPath");
		String imgPath = Play.application().configuration()
				.getString("userImgPath");
	
		String internalPath = imgRootPath + "/" + imgPath
				+ uid;
		String imagePath = Play.application().path().toString() + "/"
				+ internalPath;
	
		imagePath = imagePath.replace("\\", "/");
	
		new File(imagePath).mkdirs();
		File rename = new File(imagePath, fileName);
		
		try {
			Files.copy(file, new File(imagePath + "/" + fileName));
//			ImageFilter imf = new Canvas(Constants.USER_THUMBNAIL_MAX_WIDTH, Constants.USER_THUMBNAIL_MAX_HEIGHT, Positions.CENTER, new Color(241, 241, 241));
			Thumbnails.of(rename).size(Constants.USER_THUMBNAIL_MAX_WIDTH, Constants.USER_THUMBNAIL_MAX_HEIGHT).toFiles(Rename.PREFIX_DOT_THUMBNAIL);
			//Thumbnails.of(rename).size(Constants.USER_THUMBNAIL_MAX_WIDTH, Constants.USER_THUMBNAIL_MAX_HEIGHT).addFilter(imf).toFiles(Rename.PREFIX_DOT_THUMBNAIL);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			UserDAO.getDAO().upsert(uid,
					"baseEntity.profileimage", fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
