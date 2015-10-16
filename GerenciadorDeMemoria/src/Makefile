JCC = javac
JFLAGS = -g

default: AcessoDaMemoria.class LeituraDaEntrada.class BlocoLivre.class Processo.class Prompt.class Simulador.class GeradorArquivoBinario.class Gerenciador.class FirstFit.class NextFit.class Memoria.class MemoriaFisica.class MemoriaVirtual.class Paginacao.class NotRecentlyUsedPage.class SecondChancePage.class FirstInFirstOut.class

AcessoDaMemoria.class: AcessoDaMemoria.java
	$(JCC) $(JFLAGS) AcessoDaMemoria.java

LeituraDaEntrada.class: LeituraDaEntrada.java
	$(JCC) $(JFLAGS) LeituraDaEntrada.java

BlocoLivre.class: BlocoLivre.java
	$(JCC) $(JFLAGS) BlocoLivre.java

Processo.class: Processo.java
	$(JCC) $(JFLAGS) Processo.java

Prompt.class: Prompt.java
	$(JCC) $(JFLAGS) Prompt.java

Simulador.class: Simulador.java
	$(JCC) $(JFLAGS) Simulador.java

GeradorArquivoBinario.class: GeradorArquivoBinario.java
	$(JCC) $(JFLAGS) GeradorArquivoBinario.java

Gerenciador.class: Gerenciador.java
	$(JCC) $(JFLAGS) Gerenciador.java

FirstFit.class: FirstFit.java Gerenciador.java
	$(JCC) $(JFLAGS) FirstFit.java

NextFit.class: NextFit.java Gerenciador.java
	$(JCC) $(JFLAGS) NextFit.java

Memoria.class: Memoria.java
	$(JCC) $(JFLAGS) Memoria.java

MemoriaFisica.class: MemoriaFisica.java Memoria.java
	$(JCC) $(JFLAGS) MemoriaFisica.java

MemoriaVirtual.class: MemoriaVirtual.java Memoria.java
	$(JCC) $(JFLAGS) MemoriaVirtual.java

Paginacao.class: Paginacao.java 
	$(JCC) $(JFLAGS) Paginacao.java

NotRecentlyUsedPage.class: NotRecentlyUsedPage.java Paginacao.java
	$(JCC) $(JFLAGS) NotRecentlyUsedPage.java

SecondChancePage.class: SecondChancePage.java Paginacao.java
	$(JCC) $(JFLAGS) SecondChancePage.java

FirstinFirstOut.class: FirstInFirstOut.java Paginacao.java
	$(JCC) $(JFLAGS) FirstinFirstOut.java

clean: 
	$(RM) *.class
