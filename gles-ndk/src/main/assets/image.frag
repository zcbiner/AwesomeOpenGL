#version 300 es
precision mediump float;
in vec2 v_textureCoordinate;
uniform sampler2D u_texture;
void main() {
    gl_FragColor = texture2D(u_texture, v_textureCoordinate);
}