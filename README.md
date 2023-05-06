# Readme file for Amazing Numbers

## Introduction
Amazing Numbers is a program that can provide you with various properties of numbers. You can input a single number, a range of consecutive numbers, or a list of properties to search for. The program will then output the requested properties of the given numbers.

## How to use
To use the program, simply run it from the command line or terminal. You will be prompted to enter a request. You can enter any of the following requests:

- Enter a natural number to know its properties.
- Enter two natural numbers to obtain the properties of the list:
    - The first parameter represents a starting number.
    - The second parameter shows how many consecutive numbers are to be processed.
- Enter two natural numbers and properties to search for.
- Enter a property preceded by minus to exclude it from the results.
- Separate the parameters with one space.
- Enter 0 to exit.

Once you enter a valid request, the program will output the properties of the given numbers. Here is an example of a valid input and its output:

```
Enter a request: 1502
Properties of 1502
        buzz: false
        duck: true
        palindromic: false
        gapful: false
        spy: false
        square: false
        sunny: false
        jumping: false
        happy: false
        sad: true
        even: true
        odd: false
```

```
Enter a request: 1502 5
          1,502 is even, sad, duck
          1,503 is odd, sad, duck
          1,504 is even, sad, duck
          1,505 is odd, sad, buzz, duck
          1,506 is even, sad, duck
```

```
Enter a request: 1025 5 even sad -duck
          1,116 is even, sad
          1,118 is even, sad
          1,124 is even, spy, sad
          1,126 is even, sad
          1,132 is even, sad
```
## Supported properties
The program supports the following properties:

- buzz: If the number is divisible by 7 or has a digit of 7.
- duck: If the number contains a zero digit.
- palindromic: If the number reads the same forwards and backwards.
- gapful: If the number is divisible by the concatenation of its first and last digits.
- spy: If the sum of the digits of the number is equal to the product of the digits.
- square: If the square root of the number is an integer.
- sunny: If the next number is a square number.
- jumping: If the digits of the number are in an arithmetic sequence.
- happy: If the sum of the squares of the digits eventually equals 1.
- sad: If the sum of the squares of the digits does not eventually equal 1.
- even: If the number is even.
- odd: If the number is odd.

## License
Amazing Numbers is licensed under the MIT License. You are free to use, modify, and distribute the program as you wish. See the LICENSE file for more information.