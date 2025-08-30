#!/usr/bin/env python3
"""
创建占位符图标的脚本
由于无法直接创建PNG文件，这个脚本可以用来生成占位符图标
"""

import os
from PIL import Image, ImageDraw

def create_icon(size, filename):
    """创建一个简单的圆形图标"""
    img = Image.new('RGBA', (size, size), (0, 0, 0, 0))
    draw = ImageDraw.Draw(img)
    
    # 绘制蓝色圆形
    margin = size // 8
    draw.ellipse([margin, margin, size-margin, size-margin], 
                 fill=(98, 0, 238, 255), outline=(55, 0, 179, 255), width=2)
    
    # 绘制VPN字母
    font_size = size // 4
    text = "V"
    text_bbox = draw.textbbox((0, 0), text)
    text_width = text_bbox[2] - text_bbox[0]
    text_height = text_bbox[3] - text_bbox[1]
    text_x = (size - text_width) // 2
    text_y = (size - text_height) // 2
    
    draw.text((text_x, text_y), text, fill=(255, 255, 255, 255))
    
    img.save(filename)

# 创建不同尺寸的图标
sizes = {
    'mipmap-mdpi': 48,
    'mipmap-hdpi': 72, 
    'mipmap-xhdpi': 96,
    'mipmap-xxhdpi': 144,
    'mipmap-xxxhdpi': 192
}

base_path = 'app/src/main/res/'

for folder, size in sizes.items():
    folder_path = os.path.join(base_path, folder)
    os.makedirs(folder_path, exist_ok=True)
    
    create_icon(size, os.path.join(folder_path, 'ic_launcher.png'))
    create_icon(size, os.path.join(folder_path, 'ic_launcher_round.png'))

print("占位符图标已创建完成！")