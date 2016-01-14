JAVAC = javac

CLASSES = \
	GCodeAnalyzer.java

classes: $(CLASSES:.java=.class)

%.class: %.java
	$(JAVAC) $<

clean:
	rm -f *.class
