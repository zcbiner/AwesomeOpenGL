// 学习矩阵变换
precision mediump float;
attribute vec4 a_Position;

// 分别是位移、缩放、旋转
uniform vec2 u_Translate;
uniform float u_Scale;
uniform float u_Rotate;

void main() {
    // 4 * 4变换矩阵，对应[x,y,z,w]，注意矩阵运算。
    mat4 translateMatrix = mat4(1.0, 0.0, 0.0, 0.0,
                                  0.0, 1.0, 0.0, 0.0,
                                  0.0, 0.0, 1.0, 0.0,
                                  u_Translate.x, u_Translate.y, 0.0, 1.0);

       mat4 scaleMatrix = mat4(u_Scale, 0.0, 0.0, 0.0,
                            0.0, u_Scale, 0.0, 0.0,
                            0.0, 0.0, 1.0, 0.0,
                            0.0, 0.0, 0.0, 1.0);

       mat4 rotateMatrix = mat4(cos(u_Rotate), sin(u_Rotate), 0.0, 0.0,
                             -sin(u_Rotate), cos(u_Rotate), 0.0, 0.0,
                             0.0, 0.0, 1.0, 0.0,
                             0.0, 0.0, 0.0, 1.0);

       gl_Position = translateMatrix * rotateMatrix * scaleMatrix * a_Position;
}
