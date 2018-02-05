namespace java vng.luchm.thrift
enum Operation {
  ADD = 1,
  SUBTRACT = 2,
  MULTIPLY = 3,
  DIVIDE = 4
}
service Calculator {
  i32 calculate(1:i32 num1, 2:i32 num2, 3:Operation op);
}