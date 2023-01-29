package io.myosotisdev.minestom.util

import java.io.*

object Files
{
    fun copyDirectory(from: File, to: File)
    {
        for (file in from.listFiles())
        {
            var destFile: File = File(to.absolutePath + "\\${file.name}")

            if(file.isDirectory)
                copyDirectory(file, destFile)
            else
            {
                var fis = FileInputStream(file);
                var fos = FileOutputStream(destFile)

                var bis = BufferedInputStream(fis)
                var bos = BufferedOutputStream(fos)


                var buf = ByteArray(1024)

                var len = 0;
                while (true) {
                    len = bis.read(buf)
                    if (len == -1) break;
                    bos.write(buf, 0, len)
                }
                fis.close()
                fos.close()
            }
        }
    }
}