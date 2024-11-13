package com.lou.sao.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.lwjgl.opengl.GL11;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

public class VideoScreen extends Screen {
    private FFmpegFrameGrabber frameGrabber;
    private Java2DFrameConverter converter;
    private int textureId;
    private boolean isPlaying = true;

    public VideoScreen(String videoPath) {
        super(Text.of("Video Player"));
        try {
            frameGrabber = new FFmpegFrameGrabber(videoPath);
            frameGrabber.start();
            converter = new Java2DFrameConverter();
            textureId = GL11.glGenTextures(); // 创建OpenGL纹理ID
        } catch (Exception e) {
            e.printStackTrace();
            isPlaying = false;
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        if (isPlaying) {
            try {
                // 获取下一帧并渲染
                var frame = frameGrabber.grabImage();
                if (frame != null) {
                    var image = converter.convert(frame);
                    renderImageToScreen(context, image);
                } else {
                    // 视频播放结束
                    isPlaying = false;
                    MinecraftClient.getInstance().setScreen(null);
                }
            } catch (Exception e) {
                e.printStackTrace();
                isPlaying = false;
                MinecraftClient.getInstance().setScreen(null);
            }
        }
    }

    private void renderImageToScreen(DrawContext context, BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
    
        // 将BufferedImage转换为ByteBuffer
        int[] pixels = new int[width * height];
        image.getRGB(0, 0, width, height, pixels, 0, width);
        ByteBuffer buffer = ByteBuffer.allocateDirect(width * height * 4);
    
        for (int y = height - 1; y >= 0; y--) { // OpenGL从下往上读取
            for (int x = 0; x < width; x++) {
                int pixel = pixels[y * width + x];
                buffer.put((byte) ((pixel >> 16) & 0xFF)); // Red
                buffer.put((byte) ((pixel >> 8) & 0xFF));  // Green
                buffer.put((byte) (pixel & 0xFF));         // Blue
                buffer.put((byte) ((pixel >> 24) & 0xFF)); // Alpha
            }
        }
        buffer.flip();
    
        // 绑定并上传纹理
        RenderSystem.bindTexture(textureId);
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
    
        // 设置纹理参数
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
    
        // 绘制四边形覆盖全屏
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
    
        // 开始绘制纹理
        RenderSystem.setShaderTexture(0, textureId);
    
        // 使用OpenGL坐标直接绘制纹理
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2f(0, 0);
        GL11.glTexCoord2f(1, 0);
        GL11.glVertex2f(this.width, 0);
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex2f(this.width, this.height);
        GL11.glTexCoord2f(0, 1);
        GL11.glVertex2f(0, this.height);
        GL11.glEnd();
    
        RenderSystem.disableBlend();
    }
    

    @Override
    public void removed() {
        // 停止视频并释放资源
        try {
            frameGrabber.stop();
            frameGrabber.release();
            GL11.glDeleteTextures(textureId); // 删除纹理
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


