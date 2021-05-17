JC = javac -d ./classes/
J = java -classpath ./classes/

run: *.java
	@mkdir -p ./classes/
	$(JC) $^
	$(J) ValidityChecker

clean:
	rm -rf ./classes/
