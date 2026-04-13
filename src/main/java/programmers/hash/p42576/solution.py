def solution():
    pass  # TODO

def check(actual, expected, label):
    status = "PASS" if actual == expected else f"FAIL (expected={expected}, got={actual})"
    print(f"{label}: {status}")

if __name__ == "__main__":
    check(solution(), 0, "TC1")
