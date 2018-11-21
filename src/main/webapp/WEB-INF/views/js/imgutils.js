/**
* 对图片进行缩放然后裁剪显示
* */
function setImagSize(img,destWidth,destHeight) {
    var  imgWidth = img.width;
    var  imgHeight = img.height;
    var  widthScale = destWidth/ imgWidth;
    var  heightScale = destHeight/ imgHeight;
    var scale = widthScale > heightScale ? widthScale : heightScale;
    imgWidth = imgWidth * scale;
    imgHeight = imgHeight * scale;
    img.width = imgWidth;
    img.height = imgHeight;
}