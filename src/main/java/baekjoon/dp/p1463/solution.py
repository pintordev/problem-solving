def solve(n):
    pass  # TODO

def check(actual, expected, label):
    status = "PASS" if actual == expected else f"FAIL (expected={expected}, got={actual})"
    print(f"{label}: {status}")

if __name__ == "__main__":
    check(solve(0), 0, "TC1")

    import sys
    input = sys.stdin.readline
    n = int(input())
    print(solve(n))
