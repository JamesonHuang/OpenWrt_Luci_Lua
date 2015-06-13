CC=gcc
CFLAGS=-g -fPIC
OBJS=$(patsubst %.proto,%.o,$(wildcard *.proto))
OBJS+=nebula_proto.o
TARGET=libnebula_proto.a

all: $(TARGET)

$(TARGET):$(OBJS)
	@echo "LD $(TARGET)"
	@ar -cr $@ $^

%.o:%.proto
	@echo "make $@"
	@protoc-c $< --c_out=.
	@$(CC) $(CFLAGS) -g $(patsubst %.o,%.pb-c.c,$@) -c -o $@

%.o:%.c
	@echo "make $@"
	@$(CC) $(CFLAGS) -g $< -c -o $@

.PHONY: clean echo

clean:
	rm *.o *.a -f

echo:
	@echo "TARGET=$(TARGET)"
	@echo "OBJS=$(OBJS)"
