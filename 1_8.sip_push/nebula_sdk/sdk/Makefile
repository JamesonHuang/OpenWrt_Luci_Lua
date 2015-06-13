CC=gcc
CFLAGS=-g -fPIC -Wall -Werror
INCLUDES=-Iproto -Icrypto
LINK=-lpthread -lcurl -lssl -lcrypto
LINK+=-Lproto -lnebula_proto
LINK+=-Lcrypto
LINK+=-Llibs -ljson-c -lsnappy -lprotobuf-c -luuid -lcrypto_framwork -L/usr/lib
LINK+=-ltcmalloc_minimal
TARGET=libnebula_sdk.so
OBJS=$(patsubst %.c,%.o,$(wildcard *.c))

all:$(TARGET)

$(TARGET):$(OBJS)
	cd proto && make
	@echo LD $(TARGET)
	@$(CC) $(CFLAGS) $(OBJS) -o $(TARGET) -shared $(LINK)

.c.o:
	$(CC) $(CFLAGS) -c $< -o $@ $(INCLUDES)

.PHONY: clean

clean:
	rm $(TARGET) $(OBJS) -rf
	cd proto && make clean

install:
	install $(TARGET) /usr/lib
	mkdir -p /usr/include/nebula_sdk
	install nebula_sdk.h /usr/include/nebula_sdk
