/*******************************************************************************
 * Copyright (c) 2016-2016 CWI All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *
 * * Michael Steindorfer - Michael.Steindorfer@cwi.nl - CWI
 *******************************************************************************/
package io.usethesource.capsule;

public final class BitmapUtils {

  private static final boolean USE_SELF_WRITTEN_POPULATION_COUNT = false;

  static final long filter(long bitmap, int pattern) {
    switch (pattern) {
      case 0b01:
        return (bitmap & 0x5555555555555555L)
            & (((bitmap >> 1) & 0x5555555555555555L) ^ 0x5555555555555555L);
      case 0b10:
        return ((bitmap & 0x5555555555555555L) ^ 0x5555555555555555L)
            & ((bitmap >> 1) & 0x5555555555555555L);
      case 0b11:
        return (bitmap & 0x5555555555555555L) & ((bitmap >> 1) & 0x5555555555555555L);
      default:
        throw new IllegalArgumentException();
    }
  }

  static final int index01(final long bitmap, final long bitpos) {
    // if (USE_SELF_WRITTEN_POPULATION_COUNT) {
    // return (int) populationCountPattern01(bitmap & (bitpos - 1));
    // } else {
    final long filteredBitmap = (bitmap & 0x5555555555555555L)
        & (((bitmap >> 1) & 0x5555555555555555L) ^ 0x5555555555555555L);
    return java.lang.Long.bitCount(filteredBitmap & (bitpos - 1));
    // }
  }

  static final int index10(final long bitmap, final long bitpos) {
    // if (USE_SELF_WRITTEN_POPULATION_COUNT) {
    // return (int) populationCountPattern10(bitmap & (bitpos - 1));
    // } else {
    final long filteredBitmap = ((bitmap & 0x5555555555555555L) ^ 0x5555555555555555L)
        & ((bitmap >> 1) & 0x5555555555555555L);
    return java.lang.Long.bitCount(filteredBitmap & (bitpos - 1));
    // }
  }

  static final int index11(final long bitmap, final long bitpos) {
    // if (USE_SELF_WRITTEN_POPULATION_COUNT) {
    // return (int) populationCountPattern11(bitmap & (bitpos - 1));
    // } else {
    final long filteredBitmap =
        (bitmap & 0x5555555555555555L) & ((bitmap >> 1) & 0x5555555555555555L);
    return java.lang.Long.bitCount(filteredBitmap & (bitpos - 1));
    // }
  }

  static final long populationCountPattern01(long v) {
    long c = (v & 0x5555555555555555L) & (((v >> 1) & 0x5555555555555555L) ^ 0x5555555555555555L);
    c = (c & 0x3333333333333333L) + ((c >> 2) & 0x3333333333333333L);
    c = (c & 0x0F0F0F0F0F0F0F0FL) + ((c >> 4) & 0x0F0F0F0F0F0F0F0FL);
    c = (c & 0x00FF00FF00FF00FFL) + ((c >> 8) & 0x00FF00FF00FF00FFL);
    c = (c & 0x0000FFFF0000FFFFL) + ((c >> 16) & 0x0000FFFF0000FFFFL);
    return c;
  }

  static final long populationCountPattern10(long v) {
    long c = ((v & 0x5555555555555555L) ^ 0x5555555555555555L) & ((v >> 1) & 0x5555555555555555L);
    c = (c & 0x3333333333333333L) + ((c >> 2) & 0x3333333333333333L);
    c = (c & 0x0F0F0F0F0F0F0F0FL) + ((c >> 4) & 0x0F0F0F0F0F0F0F0FL);
    c = (c & 0x00FF00FF00FF00FFL) + ((c >> 8) & 0x00FF00FF00FF00FFL);
    c = (c & 0x0000FFFF0000FFFFL) + ((c >> 16) & 0x0000FFFF0000FFFFL);
    return c;
  }

  static final long populationCountPattern11(long v) {
    long c = (v & 0x5555555555555555L) & ((v >> 1) & 0x5555555555555555L);
    c = (c & 0x3333333333333333L) + ((c >> 2) & 0x3333333333333333L);
    c = (c & 0x0F0F0F0F0F0F0F0FL) + ((c >> 4) & 0x0F0F0F0F0F0F0F0FL);
    c = (c & 0x00FF00FF00FF00FFL) + ((c >> 8) & 0x00FF00FF00FF00FFL);
    c = (c & 0x0000FFFF0000FFFFL) + ((c >> 16) & 0x0000FFFF0000FFFFL);
    return c;
  }

}