<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:variable name="context" select="dto/context"/>
  <xsl:variable name="rootUrl" select="dto/rootUrl"/>
  <xsl:variable name="synchToken" select="dto/synchronizationToken"/>
  <xsl:variable name="id" select="dto/id"/>
  <xsl:variable name="name" select="dto/name"/>
  <xsl:variable name="oldContactId" select="dto/oldContactId"/>

  <xsl:template match="/">
    <html>
      <head>
        <title>Simple Registrator</title>
      </head>
      <body>
        <h1>Edit User</h1>
        <br/>
        <br/>
        <p/>
        <form action="{$context}{$rootUrl}" method="post" name="actionForm">
          <input type="hidden" name="actionName"/>
          <input type="hidden" name="synchToken" value="{$synchToken}"/>
          <input type="hidden" name="userId" value="{$id}"/>
          Name:
          <input type="text" name="name" value="{$name}"/>
          <p/>
          Contact:
          <select name="contact">
            <option/>
            <xsl:apply-templates select="dto/contactDTOs"/>
          </select>
          <p/>
          <a href="javascript:setActionName('save_user')">Save</a>
          <p/>
          <a href="javascript:setActionName('index')">Cancel</a>
        </form>
      </body>
      <script language="JavaScript" type="text/javascript">
        function setActionName(actionName)
        {
        document.actionForm.actionName.value = actionName;
        document.actionForm.submit();
        }
      </script>
    </html>
  </xsl:template>
  <xsl:template match="contactDTOs">
    <xsl:for-each select="contactDTO">
      <option>
        <xsl:attribute name="value">
          <xsl:value-of select="id"/>
        </xsl:attribute>
        <xsl:if test="$oldContactId=id">
          <xsl:attribute name="selected">
            true
          </xsl:attribute>
        </xsl:if>
        <xsl:value-of select="phone"/>,
        <xsl:value-of select="email"/>,
        <xsl:value-of select="icq"/>
      </option>
    </xsl:for-each>
  </xsl:template>
</xsl:stylesheet>