JAVAC = javac

CLASSES = \
	GCodeAnalyzer.java

classes: $(CLASSES:.java=.class)

%.class: %.java
	$(JAVAC) $<

test:
	./test.sh

.PHONY: test

clean:
	rm -f *.class
