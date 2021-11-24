ffmpeg -r 60 -f image2 -s 1920x1080 -i image%05d.png -vcodec libx264 -crf 25  -pix_fmt yuv420p test.mp4
::       FPS              Resolution   Imagenames            vcodec    quality                 filename
