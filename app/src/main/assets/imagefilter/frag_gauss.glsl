precision mediump float;

//在片元着色器这里添加这个 sampler2D 表示我们要添加2D贴图
uniform sampler2D u_texture;
varying vec2 v_textureCoordinate;

void main() {
    vec4 color = vec4(0.0);
    int coreSize = 3;
    int halfSize = coreSize / 2;
    float texelOffset = 0.01;
    //创建我们计算好的卷积核
    float kernel[9];
    kernel[6] = 1.0; kernel[7] = 2.0; kernel[8] = 1.0;
    kernel[3] = 2.0; kernel[4] = 4.0; kernel[5] = 2.0;
    kernel[0] = 1.0; kernel[1] = 2.0; kernel[2] = 1.0;
    int index = 0;
    //每一块都进行图像卷积。
    for(int y = 0; y < coreSize; y++) {
        for(int x = 0; x<coreSize; x++) {
            vec4 currentColor = texture2D(u_texture, v_textureCoordinate + vec2(float((-1+x)) * texelOffset, float((-1+y)) * texelOffset));
            color += currentColor * kernel[index];
            index++;
        }
    }
    //归一处理
    color /= 16.0;
    gl_FragColor=color;
}