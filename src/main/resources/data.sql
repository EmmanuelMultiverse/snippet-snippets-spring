INSERT INTO snippet (id, language, code) VALUES
(1, 'Python', 'print(''Hello, World!'')'),
(2, 'Python', 'def add(a, b):\n    return a + b'),
(3, 'Python', 'class Circle:\n    def __init__(self, radius):\n        self.radius = radius\n\n    def area(self):\n        return 3.14 * self.radius ** 2'),
(4, 'JavaScript', 'console.log(''Hello, World!'');'),
(5, 'JavaScript', 'function multiply(a, b) {\n    return a * b;\n}'),
(6, 'JavaScript', 'const square = num => num * num;'),
(7, 'Java', 'public class HelloWorld {\n    public static void main(String[] args) {\n        System.out.println("Hello, World!");\n    }\n}'),
(8, 'Java', 'public class Rectangle {\n    private int width;\n    private int height;\n\n    public Rectangle(int width, int height) {\n        this.width = width;\n        this.height = height;\n    }\n\n    public int getArea() {\n        return width * height;\n    }\n}');