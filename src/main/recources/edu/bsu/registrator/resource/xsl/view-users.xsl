<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:variable name="context" select="dto/context"/>
  <xsl:variable name="rootUrl" select="dto/rootUrl"/>
  <xsl:variable name="synchToken" select="dto/synchronizationToken"/>

  <xsl:template match="/">
    <html>
      <head>
        <title>Simple Registrator</title>
      </head>
      <body>
        <h1>View existing Users</h1>
        <br/>
        <br/>
        <p/>
        <form action="{$context}{$rootUrl}" method="post" name="actionForm">
          <input type="hidden" name="actionName"/>
          <input type="hidden" name="userId"/>
          <input type="hidden" name="synchToken" value="{$synchToken}"/>
          <xsl:apply-templates select="dto/userDTOs"/>
          <p/>
          <a href="javascript:setActionName('index')">Return to home page</a>
        </form>
      </body>
      <script language="JavaScript" type="text/javascript">
        function setActionName(actionName)
        {
        document.actionForm.actionName.value = actionName;
        document.actionForm.submit();
        }

        function setUserId(userId)
        {
        document.actionForm.userId.value = userId;
        }
      </script>
    </html>
  </xsl:template>
  <xsl:template match="userDTOs">
    <xsl:for-each select="userDTO">
      Name:<xsl:value-of select="name"/>
      <br/>
      Phone:<xsl:value-of select="phone"/>
      <br/>
      Email:<xsl:value-of select="email"/>
      <br/>
      ICQ:<xsl:value-of select="icq"/>
      <br/>
      <a>
        <xsl:attribute name="href">
          javascript:setUserId(
          <xsl:value-of select="id"/>
          );setActionName('view_user')
        </xsl:attribute>
        View
      </a>
      &#160;|&#160;
      <a>
        <xsl:attribute name="href">
          javascript:setUserId(
          <xsl:value-of select="id"/>
          );setActionName('edit_user')
        </xsl:attribute>
        Edit
      </a>
      &#160;|&#160;
      <a>
        <xsl:attribute name="href">
          javascript:setUserId(
          <xsl:value-of select="id"/>
          );setActionName('delete_user')
        </xsl:attribute>
        Delete
      </a>
      <p/>
      <p/>
    </xsl:for-each>
  </xsl:template>
</xsl:stylesheet>