JAVAC = javac

SRC = GCodeAnalyzer.java
CLASS = $(SRC:.java=.class)

classes: $(CLASS)

%.class: %.java
	$(JAVAC) $<

test: $(CLASS)
	./test.sh

test-slic3r: $(CLASS)
	./test-slic3r.sh

clean:
	rm -f *.class

.PHONY: classes test test-slic3r clean
