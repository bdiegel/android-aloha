package com.honu.aloha;

/**
 * Used with WelcomeFragment to create custom pages using the same layout file.
 *
 * Specify the resource identifiers for:
 *  - image
 *  - header text
 *  - content text
 *  - background color
 */
public class PageDescriptor {

    public int headerText;
    public int contentText;
    public int imageId;
    public int backgroundColor;

    public PageDescriptor(int headerText, int contentText, int imageId, int backgroundColor) {
        this.headerText = headerText;
        this.contentText = contentText;
        this.imageId = imageId;
        this.backgroundColor = backgroundColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getHeaderText() {
        return headerText;
    }

    public void setHeaderText(int headerText) {
        this.headerText = headerText;
    }

    public int getContentText() {
        return contentText;
    }

    public void setContentText(int contentText) {
        this.contentText = contentText;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
