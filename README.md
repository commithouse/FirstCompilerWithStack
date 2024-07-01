# Basic Compiler Developed with Java

This project is a basic compiler developed using Java. It's designed to process mathematical expressions involving addition and subtraction operations.

## Features

- The compiler can process inputs with `add` and `sub` operations. For example, it can evaluate expressions like `add(sub(5,2),add(1,3))`.
- It uses the `java.util.Stack` class to process nested expressions, ensuring correct order of operations.

## How it Works

The compiler works by parsing the input string and breaking it down into smaller sub-expressions. It then uses a stack data structure to evaluate these sub-expressions in the correct order. The result of each sub-expression is then used as an input for the outer expression, until the entire input string has been processed.

This approach allows the compiler to correctly handle nested expressions and ensure that the operations are performed in the correct order.

## Future Improvements

Future versions of this compiler may include support for more complex mathematical operations, improved error handling, and optimizations for better performance.
