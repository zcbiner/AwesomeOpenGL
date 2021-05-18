precision mediump float;
varying vec2 v_textureCoordinate;
// 2D采样器采样纹理
uniform sampler2D u_texture;
// 黑白灰度图RGB系数（浮点算法）
const highp vec3 W = vec3(0.2125, 0.7154, 0.0721);
void main() {
    vec4 mask = texture2D(u_texture, v_textureCoordinate);
    float temp = dot(mask.rgb, W);
    gl_FragColor = vec4(vec3(temp), mask.a);
}