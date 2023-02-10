.PHONY: build, test, check, report, code-coverage

build:
	@./gradlew clean build

test: # includes jacoco report
	@./gradlew test

check: # includes jacoco test coverage verification
	@./gradlew check

report:
	@./gradlew jacocotestreport

code-coverage:
	@./gradlew jacocotestcoverageverification

buildw:
	@.\gradlew clean build

testw: # includes jacoco report
	@.\gradlew test

checkw: # includes jacoco test coverage verification
	@.\gradlew check

reportw:
	@.\gradlew jacocotestreport

code-coveragew:
	@.\gradlew jacocotestcoverageverification