precision mediump float;
varying vec2 v_textureCoordinate;
// 2D采样器采样纹理
uniform sampler2D u_texture;
void main() {
    gl_FragColor = texture2D(u_texture, v_textureCoordinate);
}