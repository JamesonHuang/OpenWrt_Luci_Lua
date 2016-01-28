include $(TOPDIR)/rules.mk

PKG_NAME:=customhosts
PKG_VERSION:=1.0
PKG_RELEASE:=1

PKG_BUILD_DIR := $(BUILD_DIR)/$(PKG_NAME)

include $(INCLUDE_DIR)/package.mk

define Package/$(PKG_NAME)
        SECTION:=utils
        CATEGORY:=Utilities
        TITLE:=CustomHosts
endef

define Package/$(PKG_NAME)/description
       Custome hosts file.
endef

define Build/Prepare
endef

define Build/Configure
endef

define Build/Compile
endef

define Package/$(PKG_NAME)/conffiles
endef

define Package/$(PKG_NAME)/install
	#install shell
	$(INSTALL_DIR) $(1)/etc/init.d
	$(INSTALL_BIN) ./files/init.d/customhosts $(1)/etc/init.d/customhosts
	
	#install execute bin
	$(INSTALL_DIR) $(1)/usr/bin
	$(INSTALL_BIN) ./src/customhosts $(1)/usr/bin/customhosts
endef

define Package/$(PKG_NAME)/preinst
endef

define Package/$(PKG_NAME)/postinst
endef

define Package/$(PKG_NAME)/prerm
endef

define Package/$(PKG_NAME)/postrm
endef

$(eval $(call BuildPackage,$(PKG_NAME)))
