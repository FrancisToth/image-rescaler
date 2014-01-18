package com.imagerescaler.writer;

import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static java.lang.Double.parseDouble;

class ImageScaler {

    public void rescaleImage(InputStream rawImageStream, ByteArrayOutputStream rescaledImageStream, String ratio) throws IOException {
        BufferedImage rawImage = ImageIO.read(rawImageStream);
        BufferedImage rescaledImage = rescaleImage(rawImage, parseDouble(ratio));
        ImageIO.write(rescaledImage, "png", rescaledImageStream);
    }

    private BufferedImage rescaleImage(BufferedImage image, double ratio) {
        int imageWidth = (int) (image.getWidth() * ratio);
        int imageHeight = (int) (image.getHeight() * ratio);

        imageWidth = imageWidth == 0 ? 1 : imageWidth;
        imageHeight = imageHeight == 0 ? 1 : imageHeight;

        return Scalr.resize(image, Scalr.Mode.AUTOMATIC, imageWidth, imageHeight);
    }
}