# Unit Testing with JUnit 5 (Jupiter)

Unit testing suite for the arithmetic `Calculator` class implementing test cases with JUnit 5.

---

## Test Coverage

- **Addition**: Validated positive, negative, and zero operands (`assertEquals`).
- **Subtraction**: Validated minuend/subtrahend variations and identity subtractions (`assertEquals`).
- **Multiplication**: Validated positive/negative signs and multiplication by zero (`assertEquals`).
- **Division**: Validated integer quotient results (`assertEquals`).
- **Division by Zero Exception**: Verified that dividing by zero raises an `ArithmeticException` with descriptive message using `assertThrows`.

---

## JUnit 5 Test Run Summary

```text
╷
├─ JUnit Jupiter ✔
│  └─ CalculatorTest ✔
│     ├─ Test Division by Zero throws ArithmeticException with expected message ✔
│     ├─ Test Addition of positive and negative numbers ✔
│     ├─ Test Subtraction of positive and negative numbers ✔
│     ├─ Verify Calculator instance is initialized ✔
│     ├─ Test Division resulting in exact integers ✔
│     └─ Test Multiplication of numbers including zero ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 43 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[         6 tests found           ]
[         0 tests skipped         ]
[         6 tests started         ]
[         0 tests aborted         ]
[         6 tests successful      ]
[         0 tests failed          ]
```
