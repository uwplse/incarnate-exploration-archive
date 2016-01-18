JAVAC = javac

CLASSES = \
	GCodeAnalyzer.java

classes: $(CLASSES:.java=.class)

%.class: %.java
	$(JAVAC) $<

test: GCodeAnalyzer.class
	./test.sh

.PHONY: test

clean:
	rm -f *.class
