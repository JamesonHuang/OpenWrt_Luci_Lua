CC=gcc
CFALGS=-g
INCLUDES=-I..
LINK=-L.. -lnebula_sdk

test:test.c
	$(CC) $(CFALGS) $^ -o $@ $(INCLUDES) $(LINK)

.PHONY: clean

clean:
	rm test -f
