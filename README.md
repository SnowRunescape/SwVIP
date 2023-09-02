# SwVIP

Este é um plugin personalizado para servidores Minecraft que oferece recursos de VIP, incluindo comandos para gerenciar chaves de VIP e aplicar privilégios de VIP a jogadores.

## Comandos

### /keys

Este comando lista todas as chaves de VIP disponíveis no servidor.

### /newkey \<group\> \<days\>

Use este comando para gerar uma nova chave de VIP. Isso permitirá que os jogadores ativem o VIP usando a chave.

### /deletekey \<key\>

Este comando permite que os administradores excluam uma chave de VIP específica.

### /usekey \<key\>

Este comando permite que um jogador ative o VIP usando uma chave específica.

### /viptime

Use este comando para definir o tempo de VIP de um jogador. O tempo é especificado em minutos, horas ou dias, dependendo da configuração do servidor.

### /changevip \<grupo\>

Este comando permite que os jogadores alternem entre os VIPS.

### /givevip \<player\> \<grupo\>

Este comando permite que os administradores concedam VIP a um jogador específico.

### /removevip \<player\> \<group\>

Use este comando para remover o status de VIP de um jogador.

## Configuração

Você pode personalizar as configurações do plugin editando o arquivo de configuração `config.yml`. Lá, você pode definir grupos de VIP, duração do VIP e muito mais.

## Dependências

Este plugin pode depender de outros plugins, como um sistema de permissões ou um sistema de economia. Certifique-se de que todas as dependências necessárias estejam instaladas e configuradas corretamente antes de usar este plugin.

## Como usar

1. Baixe o arquivo JAR do plugin e coloque-o na pasta `plugins` do seu servidor Minecraft.
2. Reinicie o servidor.
3. Configure o arquivo `config.yml` de acordo com suas preferências.
4. Use os comandos mencionados acima para gerenciar as chaves de VIP e conceder VIP aos jogadores.

## Contribuições

Se você deseja contribuir para este plugin, sinta-se à vontade para fazer um fork do repositório e enviar pull requests com melhorias ou correções de bugs. Também estamos abertos a sugestões e feedback.