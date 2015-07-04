PROJ_DIR = .
INCLUDE_DIR     = $(PROJ_DIR)/include
SRC_DIR         = $(PROJ_DIR)/src
OBJ_DIR         = $(PROJ_DIR)/obj

LIBFLAGS = -pthread

INCLUDE = -I $(INCLUDE_DIR)
CFLAGS  = -fno-builtin -ggdb -c -Wall $(LIBFLAGS) $(INCLUDE)
CC      = /usr/bin/gcc
BIN	= http_cache


SRC =	$(SRC_DIR)/http_cache.c	        \
	$(SRC_DIR)/listener.c		\
	$(SRC_DIR)/tokenize.c		\
	$(SRC_DIR)/file_parser.c	\
	$(SRC_DIR)/http_config.c        \
	$(SRC_DIR)/wcache.c

#OBJ =   ${SRC:%.c=%.o}
OBJ =	$(OBJ_DIR)/http_cache.o	        \
	$(OBJ_DIR)/listener.o		\
	$(OBJ_DIR)/tokenize.o		\
	$(OBJ_DIR)/file_parser.o	\
	$(OBJ_DIR)/http_config.o        \
	$(OBJ_DIR)/wcache.o 
#---make targets
all:	$(BIN)
#%.o:	%.c
#	$(CC) $(CFLAGS) -c $< -o $(OBJ_DIR)/$@

$(OBJ_DIR)/http_cache.o: $(SRC_DIR)/http_cache.c
	$(CC) -c $(CFLAGS) $< -o $@

$(OBJ_DIR)/http_config.o: $(SRC_DIR)/http_config.c
	$(CC) -c $(CFLAGS) $< -o $@
$(OBJ_DIR)/listener.o:	$(SRC_DIR)/listener.c
	$(CC) -c $(CFLAGS) $< -o $@
$(OBJ_DIR)/tokenize.o:	$(SRC_DIR)/tokenize.c
	$(CC) -c $(CFLAGS) $< -o $@
$(OBJ_DIR)/file_parser.o: $(SRC_DIR)/file_parser.c
	$(CC) -c $(CFLAGS) $< -o $@
$(OBJ_DIR)/wcache.o: $(SRC_DIR)/wcache.c
	$(CC) -c $(CFLAGS) $< -o $@

http_cache: $(OBJ)
	$(CC) $(LIBFLAGS) -o $(OBJ_DIR)/$(BIN) $(OBJ) 

.PHONY : clean
clean:	
	rm -f $(OBJ_DIR)/*.o
	rm -f $(OBJ_DIR)/http_cache
